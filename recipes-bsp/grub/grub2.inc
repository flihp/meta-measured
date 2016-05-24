SUMMARY = "GRUB2 is the next-generation GRand Unified Bootloader"

DESCRIPTION = "GRUB2 is the next generaion of a GPLed bootloader \
intended to unify bootloading across x86 operating systems. In \
addition to loading the Linux kernel, it implements the Multiboot \
standard, which allows for flexible loading of multiple boot images."

HOMEPAGE = "http://www.gnu.org/software/grub/"
SECTION = "bootloaders"

LICENSE = "GPLv3"
LIC_FILES_CHKSUM = "file://COPYING;md5=d32239bcb673463ab874e80d47fae504"

DEPENDS = "flex-native bison-native"

COMPATIBLE_HOST = '(x86_64.*|i.86.*)-(linux|freebsd.*)'

inherit autotools gettext texinfo

PACKAGECONFIG ??= ""
PACKAGECONFIG[grub-mount] = "--enable-grub-mount,--disable-grub-mount,fuse"
PACKAGECONFIG[device-mapper] = "--enable-device-mapper,--disable-device-mapper,lvm2"

# grub2 creates its own set of -nostdinc / -isystem / -ffreestanding CFLAGS and
# OE's default BUILD_CFLAGS (assigned to CFLAGS for native builds) etc, conflict
# with that. Note that since BUILD_CFLAGS etc are not used by grub2 target
# builds, it's safe to clear them unconditionally for both target and native.
BUILD_CPPFLAGS = ""
BUILD_CFLAGS = ""
BUILD_CXXFLAGS = ""
BUILD_LDFLAGS = ""

do_configure_prepend() {
	# The grub2 configure script uses variables such as TARGET_CFLAGS etc
	# for its own purposes. Remove the OE versions from the environment to
	# avoid conflicts.
	currdir=`pwd`
	cd ${S}
	./autogen.sh
	cd $currdir
	unset TARGET_CPPFLAGS TARGET_CFLAGS TARGET_CXXFLAGS TARGET_LDFLAGS
}

# grub and grub-efi's sysroot/${datadir}/grub/grub-mkconfig_lib are
# conflicted, remove it since no one uses it.
SYSROOT_PREPROCESS_FUNCS_class-target += "remove_sysroot_mkconfig_lib"
remove_sysroot_mkconfig_lib() {
    rm -r "${SYSROOT_DESTDIR}${datadir}/grub/grub-mkconfig_lib"
}
