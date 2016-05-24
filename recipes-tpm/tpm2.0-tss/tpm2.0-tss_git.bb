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

SRC_URI = " \
    git://github.com/01org/TPM2.0-TSS.git;protocol=git;branch=master;name=TPM2.0-TSS;destsuffix=TPM2.0-TSS \
    file://no-cmocka-configure-ac.diff \
    file://vpath-fix-for-pc-files.diff \
    file://no-wall-werror.diff \
    "

# CAPS? SRSLY?
S = "${WORKDIR}/${@d.getVar('BPN',d).upper()}"

# https://lists.yoctoproject.org/pipermail/yocto/2013-November/017042.html
SRCREV = "${AUTOREV}"
PVBASE := "${PV}"
PV = "${PVBASE}.${SRCPV}"

PROVIDES = "${PACKAGES}"
PACKAGES = " \
    ${PN}-dbg \
    libtss2 \
    libtss2-dev \
    libtss2-staticdev \
    libtctidevice \
    libtctidevice-dev \
    libtctidevice-staticdev \
    libtctisocket \
    libtctisocket-dev \
    libtctisocket-staticdev \
    resourcemgr \
"

FILES_libtss2 = "${libdir}/libtss2.so.0.0.0"
FILES_libtss2-dev = " \
    ${includedir}/tss2 \
    ${includedir}/tcti/common.h \
    ${libdir}/libtss2.so* \
    ${libdir}/pkgconfig/tss2.pc \
"
FILES_libtss2-staticdev = " \
    ${libdir}/libtss2.a \
    ${libdir}/libtss2.la \
"
FILES_libtctidevice = "${libdir}/libtctidevice.so.0.0.0"
FILES_libtctidevice-dev = " \
    ${includedir}/tcti/tcti_device.h \
    ${libdir}/libtctidevice.so* \
    ${libdir}/pkgconfig/tcti_device.pc \
"
FILES_libtctidevice-staticdev = "${libdir}/libtctidevice.*a"
FILES_libtctisocket = "${libdir}/libtctisocket.so.0.0.0"
FILES_libtctisocket-dev = " \
    ${includedir}/tcti/tcti_socket.h \
    ${libdir}/libtctisocket.so* \
    ${libdir}/pkgconfig/tcti_socket.pc \
"
FILES_libtctisocket-staticdev = "${libdir}/libtctisocket.*a"
FILES_resourcemgr = "${sbindir}/resourcemgr"

inherit autotools

# the autotools / autoconf-archive don't work as expected so we include the
# pthread macro ourselves for now
SRC_URI += "file://ax_pthread.m4"
do_configure_prepend () {
	mkdir -p ${S}/m4
	cp ${WORKDIR}/ax_pthread.m4 ${S}/m4
	# execute the bootstrap script
	currentdir=$(pwd)
	cd ${S}
	./bootstrap --force
	cd ${currentdir}
}

