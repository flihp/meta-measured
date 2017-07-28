include ${BPN}.inc

LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/BSD-2-Clause;md5=8bef8e6712b1be5aa76af1ebde9d6378"
SRC_URI[md5sum] = "7711009052871f7b57f2273a10f34262"
SRC_URI[sha256sum] = "aa5169f85bf36754d6ade6f729211cfd1ba9645c629906085e8da2b166c54909"
SRC_URI += "https://github.com/01org/tpm2-abrmd/releases/download/${PV}/${BPN}-${PV}.tar.gz"
