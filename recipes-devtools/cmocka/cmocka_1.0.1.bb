SUMMARY = "C unit test framework"
DESCRIPTION = "C unit test framework"
SECTION = "devtools"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://COPYING;md5=3b83ef96387f14655fc854ddc3c6bd57"

SRC_URI = "https://cmocka.org/files/1.0/cmocka-1.0.1.tar.xz"
SRC_URI[md5sum] = "ed861e501a21a92b2af63e466df2015e"

S = "${WORKDIR}/cmocka-1.0.1"

inherit pkgconfig cmake

