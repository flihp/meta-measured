SUMMARY = "TrouSerS - An open-source TCG Software Stack implementation, created and released by IBM."
DESCRIPTION = " \
  Trousers is an open-source TCG Software Stack (TSS), released under the \
  Common Public License. Trousers aims to be compliant with the current (1.1b) \
  and upcoming (1.2) TSS specifications available from the Trusted Computing \
  Group website: http://www.trustedcomputinggroup.org. \
  "
SECTION = "tpm"
PR = "r0"
LICENSE = "CPL-1.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=3728dd9198d68b49f7f9ed1f3f50ba14"
DEPENDS = "openssl"

SRC_URI += "http://sourceforge.net/projects/trousers/files/trousers/0.3.10/trousers-0.3.10.tar.gz \
            file://trousers.init.sh \
            file://trousers-udev.rules \
"

SRC_URI[md5sum] = "27b7374d991874b4a0a973b1c952c79f"
SRC_URI[sha256sum] = "eb9569de5c66d9698f6c3303de03777b95ec72827f68b7744454bfa9227bc530"

PROVIDES = "${PACKAGES}"
PACKAGES = " \
	libtspi \
	libtspi-dbg \
	libtspi-dev \
	libtspi-doc \
	libtspi-staticdev \
	trousers \
	trousers-dbg \
	trousers-doc \
	"

FILES_libtspi = " \
	${libdir}/*.so.1.2.0 \
	"
FILES_libtspi-dbg = " \
	${libdir}/.debug \
	${prefix}/src/debug/${PN}/${PV}-${PR}/${PN}-${PV}/src/tspi \
	${prefix}/src/debug/${PN}/${PV}-${PR}/${PN}-${PV}/src/trspi \
	${prefix}/src/debug/${PN}/${PV}-${PR}/${PN}-${PV}/src/include/*.h \
	${prefix}/src/debug/${PN}/${PV}-${PR}/${PN}-${PV}/src/include/tss \
	"
FILES_libtspi-dev = " \
	${includedir} \
	${libdir}/*.so \
	${libdir}/*.so.1 \
	"
FILES_libtspi-doc = " \
	${mandir}/man3 \
	"
FILES_libtspi-staticdev = " \
	${libdir}/*.la \
	${libdir}/*.a \
	"
FILES_${PN} = " \
	${sbindir}/tcsd \
	${sysconfdir} \
	${localstatedir} \
	"
FILES_${PN}-dbg = " \
	${sbindir}/.debug \
	${prefix}/src/debug/${PN}/${PV}-${PR}/${PN}-${PV}/src/tcs \
	${prefix}/src/debug/${PN}/${PV}-${PR}/${PN}-${PV}/src/tcsd \
	${prefix}/src/debug/${PN}/${PV}-${PR}/${PN}-${PV}/src/tddl \
	${prefix}/src/debug/${PN}/${PV}-${PR}/${PN}-${PV}/src/trousers \
	${prefix}/src/debug/${PN}/${PV}-${PR}/${PN}-${PV}/src/include/trousers \
	"
FILES_${PN}-doc = " \
	${mandir}/man5 \
	${mandir}/man8 \
	"

inherit autotools pkgconfig useradd

EXTRA_OECONF="--with-gui=none"

USERADD_PACKAGES = "${PN}"
GROUPADD_PARAM_${PN} = "tss"
USERADD_PARAM_${PN} = "-M -d /var/lib/tpm -s /bin/false -g tss tss"

do_install_append() {
        install -d ${D}${sysconfdir}/init.d
        install -m 0755 ${WORKDIR}/trousers.init.sh ${D}${sysconfdir}/init.d/trousers
        install -d ${D}${sysconfdir}/udev/rules.d
        install -m 0644 ${WORKDIR}/trousers-udev.rules ${D}${sysconfdir}/udev/rules.d/45-trousers.rules
}
