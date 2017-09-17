SUMMARY = "Software stack for TPM2."
DESCRIPTION = "tpm2-tss like woah."
SECTION = "tpm"

LICENSE = "BSD-2-Clause"
LIC_FILES_CHKSUM = "file://${S}/LICENSE;md5=500b2e742befc3da00684d8a1d5fd9da"

DEPENDS = "autoconf-archive-native"

S = "${WORKDIR}/${BPN}"

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
"

FILES_libtss2 = " \
    ${libdir}/libsapi.so.0.0.0 \
    ${libdir}/libmarshal.so.0.0.0 \
"
FILES_libtss2-dev = " \
    ${includedir}/sapi \
    ${includedir}/tcti/common.h \
    ${libdir}/libmarshal.so* \
    ${libdir}/libsapi.so* \
    ${libdir}/pkgconfig/sapi.pc \
"
FILES_libtss2-staticdev = " \
    ${libdir}/libmarshal.a \
    ${libdir}/libmarshal.la \
    ${libdir}/libsapi.a \
    ${libdir}/libsapi.la \
"
FILES_libtctidevice = "${libdir}/libtcti-device.so.0.0.0"
FILES_libtctidevice-dev = " \
    ${includedir}/tcti/tcti_device.h \
    ${libdir}/libtcti-device.so* \
    ${libdir}/pkgconfig/tcti-device.pc \
"
FILES_libtctidevice-staticdev = "${libdir}/libtcti-device.*a"
FILES_libtctisocket = "${libdir}/libtcti-socket.so.0.0.0"
FILES_libtctisocket-dev = " \
    ${includedir}/tcti/tcti_socket.h \
    ${libdir}/libtcti-socket.so* \
    ${libdir}/pkgconfig/tcti-socket.pc \
"
FILES_libtctisocket-staticdev = "${libdir}/libtcti-socket.*a"

inherit autotools pkgconfig

do_configure_prepend () {
	# Execute the bootstrap script, to generate src_vars.mk.
	# The actual autotools bootstrapping is done by the normal
	# do_configure, which does a better job with it (for example,
	# it finds m4 macros also in the native sysroot).
	currentdir=$(pwd)
	cd ${S}
	AUTORECONF=true ./bootstrap
	cd ${currentdir}
}
