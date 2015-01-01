include acm.inc

SUMMARY = "ACM for Intel Haswell (HSW) machines"
LIC_FILES_CHKSUM = "file://license.txt;md5=a879c484244808a2202d65166a2f3f72"

S = "${WORKDIR}/4th_gen_i5_i7-SINIT_${PV}"

SRC_URI[md5sum] = "e1fbdd2b8c255d3ded6f33d14e30d498"
SRC_URI[sha256sum] = "63ad2d0b8fdb4422e0f751c23472a0e9bfbc3c643959e21249c66336943b910b"

COMPATIBLE_HOST = '(x86_64|i.86).*-(linux|freebsd.*)'
SRC_FILE="4th_gen_i5_i7_SINIT_${PV}.BIN"
DST_FILE="acm_hsw.bin"
