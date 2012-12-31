# Intel laywers have the download for the binary ACMs hidden behind a license
# click through.  You'll have to manually download it and put it in your OE
# download directory before this recipe will work.

SUMMARY = "ACM for Intel Sugary Bay machines"
DESCRIPTION = " \
Intel® Trusted Execution Technology (Intel® TXT) provides a hardware- based \
root of trust to ensure that a platform boots with a known good configuration \
of firmware, BIOS, virtual machine monitor, and operating system. For more \
information, please refer to \
http://www.intel.com/technology/malwarereduction/index.htm \
  "
SECTION = "tpm"
PR = "r0"
LICENSE = "binary"
LIC_FILES_CHKSUM = "file://license.txt;md5=60d123634e0b94f8c425003389e64bda"

S = "${WORKDIR}/2nd_gen_i5_i7-SINIT_51"

SRC_URI += " \
    https://secure-software.intel.com/en-us/system/files/article/183305/2nd-gen-i5-i7-sinit-51.zip \
    "

SRC_URI[md5sum] = "7531c7b81e9b3da748ad992cd8834718"
SRC_URI[sha256sum] = "8b5c39a489a57f51415fd1da14a4d357f254241fa3c2d4c1394d89bfb8087428"

COMPATIBLE_HOST = '(x86_64|i.86).*-(linux|freebsd.*)'

do_install() {
    install -d ${D}/boot
    install -m 0644 ${S}/2nd_gen_i5_i7_SINIT_51.BIN ${D}/boot
}

FILES_${PN} = "${base_prefix}/boot"

sysroot_stage_all_append() {
	sysroot_stage_dir ${D}/boot ${SYSROOT_DESTDIR}/kernel

	install -d ${DEPLOY_DIR_IMAGE}
	install -m 0644 ${D}/boot/2nd_gen_i5_i7_SINIT_51.BIN ${DEPLOY_DIR_IMAGE}/acm.bin
}
