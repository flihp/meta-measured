SUMMARY = "Tools for TPM2."
DESCRIPTION = "tpm2.0-tools"
SECTION = "tpm"

LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://${S}/LICENSE;md5=91b7c548d73ea16537799e8060cea819"
DEPENDS = "tpm2.0-tss openssl curl"
SRC_URI = "git://github.com/01org/tpm2.0-tools.git;protocol=git;branch=master;name=tpm2.0-tools;destsuffix=tpm2.0-tools"

S = "${WORKDIR}/tpm2.0-tools"
# https://lists.yoctoproject.org/pipermail/yocto/2013-November/017042.html
SRCREV = "${AUTOREV}"
PVBASE := "${PV}"
PV = "${PVBASE}.${SRCPV}"

inherit autotools

do_configure_prepend () {
	# execute the bootstrap script
	currentdir=$(pwd)
	cd ${S}
	./bootstrap --force
	cd ${currentdir}
}
