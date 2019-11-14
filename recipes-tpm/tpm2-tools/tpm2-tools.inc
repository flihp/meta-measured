SUMMARY = "Tools for TPM2"
DESCRIPTION = "This project builds a set of command-line tools for \
interacting with a TPM (Trusted Platform Module) 2."
HOMEPAGE = "https://github.com/tpm2-software/tpm2-tools"
SECTION = "security/tpm"

LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://${S}/LICENSE;md5=0eb1216e46938bd723098d93a23c3bcc"
DEPENDS = "tpm2-abrmd tpm2-tss openssl curl autoconf-archive-native"

inherit autotools pkgconfig bash-completion

SRC_URI = "https://github.com/tpm2-software/${BPN}/releases/download/${PV}/${BPN}-${PV}.tar.gz"
