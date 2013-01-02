# Intel laywers have the download for the binary ACMs hidden behind a license
# click through.  You'll have to manually download it and put it in your OE
# download directory before this recipe will work.

include acm.inc

SUMMARY = "ACM for Intel Ivy Bridge (IVB) machines"
PR = "r0"
LIC_FILES_CHKSUM = "file://license.txt;md5=0dcc820fe5b2241bd79ae82cbda963e5"

S = "${WORKDIR}/3rd_gen_i5_i7-SINIT_51"

SRC_URI += " \
    https://secure-software.intel.com/en-us/system/files/article/183305/3rd-gen-i5-i7-sinit-51.zip \
    "

SRC_URI[md5sum] = "6037fe7e59bbb0297c13fcb57aee0fcf"
SRC_URI[sha256sum] = "4de579ffb0a930611a006dc34d9b9e1300b769e92e1b3b7a890b1155a13aa99d"

COMPATIBLE_HOST = '(x86_64|i.86).*-(linux|freebsd.*)'
SRC_FILE="3rd_gen_i5_i7_SINIT_51.BIN"
DST_FILE="acm_ivb.bin"
