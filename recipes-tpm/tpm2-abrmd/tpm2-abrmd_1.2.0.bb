include ${BPN}.inc

LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/BSD-2-Clause;md5=8bef8e6712b1be5aa76af1ebde9d6378"
SRC_URI[md5sum] = "7d74d2718f48c9f791f7c7f51d329d03"
SRC_URI[sha256sum] = "e20d2796c3097f9eec8410cec6a99d1532769d1cc138d6d9331c8ee1f0d305a4"
SRC_URI += "https://github.com/tpm2-software/${BPN}/releases/download/${PV}/${BPN}-${PV}.tar.gz"
