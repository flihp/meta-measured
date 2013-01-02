# Intel laywers have the download for the binary ACMs hidden behind a license
# click through.  You'll have to manually download it and put it in your OE
# download directory before this recipe will work.

include acm.inc

SUMMARY = "ACM for Intel Sugary Bay machines"
PR = "r0"
LIC_FILES_CHKSUM = "file://license.txt;md5=60d123634e0b94f8c425003389e64bda"

S = "${WORKDIR}/2nd_gen_i5_i7-SINIT_51"

SRC_URI += " \
    https://secure-software.intel.com/en-us/system/files/article/183305/2nd-gen-i5-i7-sinit-51.zip \
    "

SRC_URI[md5sum] = "7531c7b81e9b3da748ad992cd8834718"
SRC_URI[sha256sum] = "8b5c39a489a57f51415fd1da14a4d357f254241fa3c2d4c1394d89bfb8087428"

COMPATIBLE_HOST = '(x86_64|i.86).*-(linux|freebsd.*)'
SRC_FILE="2nd_gen_i5_i7_SINIT_51.BIN"
DST_FILE="acm.bin"
