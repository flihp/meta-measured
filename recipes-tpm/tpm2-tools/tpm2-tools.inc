SUMMARY = "Tools for TPM2."
DESCRIPTION = "tpm2-tools"
SECTION = "tpm"

LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://${S}/LICENSE;md5=91b7c548d73ea16537799e8060cea819"
DEPENDS = "tpm2-tss openssl curl autoconf-archive-native"

S = "${WORKDIR}/${BPN}-${PV}"
inherit autotools pkgconfig

SRC_URI = "https://github.com/01org/${BPN}/archive/${PV}.tar.gz"
