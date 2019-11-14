SUMMARY = "The TCG TPM2 Software Stack"
DESCRIPTION = "The TCG (Trusted Computing Group) Software Stack (TSS) \
provides a standard API for accessing the functions of the TPM. \
Application developers can use this software to develop interoperable \
client applications."
HOMEPAGE = "https://github.com/tpm2-software/tpm2-tss"
SECTION = "security/tpm"

LICENSE = "BSD-2-Clause"
LIC_FILES_CHKSUM = "file://${S}/LICENSE;md5=500b2e742befc3da00684d8a1d5fd9da"

SRC_URI = "https://github.com/tpm2-software/${BPN}/releases/download/${PV}/${BPN}-${PV}.tar.gz"

DEPENDS = "autoconf-archive-native openssl"
RDEPENDS_libtss2 = "openssl"

S = "${WORKDIR}/${BPN}-${PV}"

EXTRA_OECONF += "--disable-doxygen-doc"

PROVIDES = "${PACKAGES}"
PACKAGES = " \
    ${PN}-dbg \
    ${PN}-doc \
    libtss2 \
    libtss2-dev \
    libtss2-staticdev \
    libtss2-doc \
"

FILES_libtss2 = " \
    ${libdir}/libtss2-esys.so.* \
    ${libdir}/libtss2-mu.so.* \
    ${libdir}/libtss2-sys.so.* \
    ${libdir}/libtss2-tcti-device.so.* \
    ${libdir}/libtss2-tcti-mssim.so.* \
    ${libdir}/libtss2-tctildr.so.* \
    ${libdir}/libtss2-rc.so.* \
    ${libdir}/udev \
"

FILES_libtss2-dev = " \
    ${includedir}/tss2 \
    ${libdir}/libtss2-esys.so \
    ${libdir}/libtss2-mu.so \
    ${libdir}/libtss2-sys.so \
    ${libdir}/libtss2-tcti-device.so \
    ${libdir}/libtss2-tcti-mssim.so \
    ${libdir}/libtss2-tctildr.so \
    ${libdir}/libtss2-rc.so \
    ${libdir}/pkgconfig/tss2-esys.pc \
    ${libdir}/pkgconfig/tss2-mu.pc \
    ${libdir}/pkgconfig/tss2-sys.pc \
    ${libdir}/pkgconfig/tss2-tcti-device.pc \
    ${libdir}/pkgconfig/tss2-tcti-mssim.pc \
    ${libdir}/pkgconfig/tss2-tctildr.pc \
    ${libdir}/pkgconfig/tss2-rc.so \
    ${FILES_tpm2-tss-dev} \
"

FILES_libtss2-staticdev = " \
    ${libdir}/libtss2-esys.a \
    ${libdir}/libtss2-mu.a \
    ${libdir}/libtss2-sys.a \
    ${libdir}/libtss2-tcti-device.a \
    ${libdir}/libtss2-tcti-mssim.a \
    ${libdir}/libtss2-tctildr.a \
    ${libdir}/libtss2-rc.a \
"

FILES_libtss2-doc = " \
    ${mandir}/man3 \
    ${mandir}/man7 \
"

inherit autotools pkgconfig
