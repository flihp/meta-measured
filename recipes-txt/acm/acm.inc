DESCRIPTION = " \
Intel® Trusted Execution Technology (Intel® TXT) provides a hardware- based \
root of trust to ensure that a platform boots with a known good configuration \
of firmware, BIOS, virtual machine monitor, and operating system. For more \
information, please refer to \
http://www.intel.com/technology/malwarereduction/index.htm \
  "
SECTION = "tpm"
LICENSE = "Proprietary"

COMPATIBLE_HOST = '(x86_64|i.86).*-(linux|freebsd.*)'

# The Intel site hides SINIT ACMs behind a "EULA wall". Mirroring them was
#   less work than hacking a fetcher to side-step Intel's roadblock.
SRC_URI += " \
    http://twobit.us/mirrors/acms/${BP}.zip \
    "

inherit deploy

do_install() {
    install -d ${D}/boot
    install -m 0644 ${S}/${SRC_FILE} ${D}/boot
}

FILES_${PN} = "${base_prefix}/boot"

do_deploy() {
	install -m 0644 ${D}/boot/${SRC_FILE} ${DEPLOYDIR}/${DST_FILE}
}

do_deploy_class-native() {
	:
}

sysroot_stage_all_append() {
	sysroot_stage_dir ${D}/boot ${SYSROOT_DESTDIR}/kernel
}

addtask deploy after do_install before do_build
