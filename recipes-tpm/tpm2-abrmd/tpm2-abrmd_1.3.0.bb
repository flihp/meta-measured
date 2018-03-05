include ${BPN}.inc

LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/BSD-2-Clause;md5=8bef8e6712b1be5aa76af1ebde9d6378"
SRC_URI[md5sum] = "92fc1e0c6a3d028728cfd31a5f705651"
SRC_URI[sha256sum] = "e32e19de93b539374a485d9df7fe9415ce147ec03c8d9ba6593e50f7a67a7a51"
SRC_URI += "https://github.com/tpm2-software/${BPN}/releases/download/${PV}/${BPN}-${PV}.tar.gz"
