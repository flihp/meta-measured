DESCRIPTION = " \
Intel® Trusted Execution Technology (Intel® TXT) provides a hardware- based \
root of trust to ensure that a platform boots with a known good configuration \
of firmware, BIOS, virtual machine monitor, and operating system. For more \
information, please refer to \
http://www.intel.com/technology/malwarereduction/index.htm \
  "
SECTION = "tpm"
LICENSE = "binary"

COMPATIBLE_HOST = '(x86_64|i.86).*-(linux|freebsd.*)'

# The Intel site hides SINIT ACMs behind a "EULA wall". Mirroring them was
#   less work than hacking a fetcher to side-step Intel's roadblock.
SRC_URI += " \
    http://twobit.us/mirrors/acms/${BP}.zip \
    "

do_install() {
    install -d ${D}/boot
    install -m 0644 ${S}/${SRC_FILE} ${D}/boot
}

FILES_${PN} = "${base_prefix}/boot"

sysroot_stage_all_append() {
	sysroot_stage_dir ${D}/boot ${SYSROOT_DESTDIR}/kernel

	install -d ${DEPLOY_DIR_IMAGE}
	install -m 0644 ${D}/boot/${SRC_FILE} ${DEPLOY_DIR_IMAGE}/${DST_FILE}
}
