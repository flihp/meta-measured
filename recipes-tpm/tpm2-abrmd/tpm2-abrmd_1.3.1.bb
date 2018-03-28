include ${BPN}.inc

LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/BSD-2-Clause;md5=8bef8e6712b1be5aa76af1ebde9d6378"
SRC_URI[md5sum] = "3f5f2461fd98aca0add1187e4705c0de"
SRC_URI[sha256sum] = "859d777a0d2c5d78309c4a2f06879a1e914b41324ea8258920a778a1ad7e38ea"
SRC_URI += "https://github.com/tpm2-software/${BPN}/releases/download/${PV}/${BPN}-${PV}.tar.gz"
