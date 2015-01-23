include acm.inc

SUMMARY = "ACM for Intel Broadwell machines"
LIC_FILES_CHKSUM = "file://license.txt;md5=68248a22232ba4fd23010e9c65209406"

SRC_URI = "http://twobit.us/mirrors/acms/5th_gen_i5_i7-SINIT_${PV}.zip"
S = "${WORKDIR}/5th_gen_i5_i7-SINIT_${PV}"

SRC_URI[md5sum] = "f40771addcb12c82b44c2ad53dbbe994"
SRC_URI[sha256sum] = "3057efadd6bcf9ddf192c6aa027cc28e07ae6997a5c0037ef1fa09e8938893f0"

COMPATIBLE_HOST = '(x86_64|i.86).*-(linux|freebsd.*)'
SRC_FILE="5th_gen_i5_i7_SINIT_${PV}.BIN"
DST_FILE="acm_bdw.bin"

