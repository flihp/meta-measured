include ${BPN}.inc

LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/BSD-2-Clause;md5=8bef8e6712b1be5aa76af1ebde9d6378"
SRC_URI[md5sum] = "07b629d99685b4273a85e894e623e87b"
SRC_URI[sha256sum] = "18d407d6752d1d3af9a1bc811074708e9873024eb661be8d50759b3d26ffba5f"
SRC_URI += "https://github.com/tpm2-software/${BPN}/releases/download/${PV}/${BPN}-${PV}.tar.gz"
