include acm.inc

SUMMARY = "ACM for Intel Ivy Bridge (IVB) machines"
LIC_FILES_CHKSUM = "file://license.txt;md5=a879c484244808a2202d65166a2f3f72"

S = "${WORKDIR}/3rd_gen_i5_i7-SINIT_${PV}"

SRC_URI[md5sum] = "b5d19bc8ac2185e3fa88d78d80a3e254"
SRC_URI[sha256sum] = "8618d72c00a824d693836d1334330e1cf831c952bb541a93a5e70e54c9e479a2"

COMPATIBLE_HOST = '(x86_64|i.86).*-(linux|freebsd.*)'
SRC_FILE="3rd_gen_i5_i7_SINIT_${PV}.BIN"
DST_FILE="acm_ivb.bin"
