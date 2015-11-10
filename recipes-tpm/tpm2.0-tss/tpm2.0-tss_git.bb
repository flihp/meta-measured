SUMMARY = "Software stack for TPM2."
DESCRIPTION = "tpm2.0-tss like woah."
SECTION = "tpm"

# This is a lie. The source for this project is covered by several licenses.
# We're currently working on a way to make this clear for those consuming the
# project. Till then I'm using 'BSD' as a place holder since the Intel license
# is "BSD-like".
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/files/common-licenses/BSD;md5=3775480a712fc46a69647678acb234cb"

# This doesn't seem to work. Keeping it here for completeness. Remove once
# it's fixed upstream.
DEPENDS = "autoconf-archive"

SRC_URI = "git://github.com/01org/TPM2.0-TSS.git;protocol=git;branch=master;name=TPM2.0-TSS;destsuffix=TPM2.0-TSS"

# CAPS? SRSLY?
S = "${WORKDIR}/${@d.getVar('BPN',d).upper()}"

# https://lists.yoctoproject.org/pipermail/yocto/2013-November/017042.html
SRCREV = "${AUTOREV}"
PVBASE := "${PV}"
PV = "${PVBASE}.${SRCPV}"

inherit autotools autotools-bootstrap

# the autotools / autoconf-archive don't work as expected so we include the
# pthread macro ourselves for now
SRC_URI += "file://ax_pthread.m4"
do_configure_prepend () {
	mkdir -p ${S}/m4
	cp ${WORKDIR}/ax_pthread.m4 ${S}/m4
}

