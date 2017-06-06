require grub2.inc

DEFAULT_PREFERENCE = "1"

DEPENDS_class-target = "grub-efi-native"
RDEPENDS_${PN}_class-target = "diffutils freetype"

PV = "2.02-beta3"
SRCREV = "${PV}"
SRC_URI = " \
    git://git.savannah.gnu.org/grub.git;protocol=git;branch=master;name=grub;destsuffix=grub \
    file://0001-tpm2-Add-types-and-functions-to-use-UEFI-TCG-command.patch \
    file://0002-tpm2cmd-Add-new-commands-for-basic-interaction-with-.patch \
    file://0003-tpm2cmd-Add-sendlog-command.patch \
    file://0004-Rework-linux-command.patch \
    file://0005-linux-Measure-the-kernel-image.patch \
    file://0006-linux-Measure-kernel-command-line.patch \
    file://0007-initrd-Measure-initrd-files-as-they-re-loaded.patch \
    file://0008-dl-Measure-dynamic-modules.patch \
    file://0009-execute-Measure-commands.patch \
    file://0001-grub.d-10_linux.in-add-oe-s-kernel-name.patch \
    file://0001-Disable-mfpmath-sse-as-well-when-SSE-is-disabled.patch \
    file://sysmacros.patch \
    file://autogen.sh-exclude-pc.patch \
    file://cfg \
"

S = "${WORKDIR}/grub"

## Determine the target arch for the grub modules
python __anonymous () {
    import re
    target = d.getVar('TARGET_ARCH', True)
    if target == "x86_64":
        grubtarget = 'x86_64'
        grubimage = "grub-efi-bootx64.efi"
    elif re.match('i.86', target):
        grubtarget = 'i386'
        grubimage = "grub-efi-bootia32.efi"
    else:
        raise bb.parse.SkipPackage("grub-efi is incompatible with target %s" % target)
    d.setVar("GRUB_TARGET", grubtarget)
    d.setVar("GRUB_IMAGE", grubimage)
}

inherit deploy

CACHED_CONFIGUREVARS += "ac_cv_path_HELP2MAN="
EXTRA_OECONF = "--with-platform=efi --disable-grub-mkfont \
                --enable-efiemu=no --program-prefix='' \
                --enable-liblzma=no --enable-device-mapper=no --enable-libzfs=no"

EXTRA_OECONF += "${@bb.utils.contains('DISTRO_FEATURES', 'largefile', '--enable-largefile', '--disable-largefile', d)}"

# ldm.c:114:7: error: trampoline generated for nested function 'hook' [-Werror=trampolines]
# and many other places in the grub code when compiled with some native gcc compilers (specifically, gentoo)
CFLAGS_append_class-native = " -Wno-error=trampolines"

do_install_class-native() {
	install -d ${D}${bindir}
	install -m 755 grub-mkimage ${D}${bindir}
}

GRUB_BUILDIN ?= "boot linux ext2 fat serial part_msdos part_gpt normal efi_gop iso9660 search"

do_deploy() {
	# Search for the grub.cfg on the local boot media by using the
	# built in cfg file provided via this recipe
	grub-mkimage -c ../cfg -p /EFI/BOOT -d ./grub-core/ \
	               -O ${GRUB_TARGET}-efi -o ./${GRUB_IMAGE} \
	               ${GRUB_BUILDIN}
	install -m 644 ${B}/${GRUB_IMAGE} ${DEPLOYDIR}
}

do_deploy_class-native() {
	:
}

addtask deploy after do_install before do_build

FILES_${PN} += "${libdir}/grub/${GRUB_TARGET}-efi \
                ${datadir}/grub \
                "

FILES_${PN}-dbg += "${libdir}/grub/${GRUB_TARGET}-efi/.debug"

BBCLASSEXTEND = "native"

