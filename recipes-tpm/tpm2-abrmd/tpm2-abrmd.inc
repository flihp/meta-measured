SUMMARY = "TPM2 Access Broker & Resource Manager"
DESCRIPTION = "This is a system daemon implementing the TPM2 Access Broker (AB) \
and Resource Manager (RM) specification from the TCG (Trusted Computing Group)."
HOMEPAGE = "https://github.com/tpm2-software/tpm2-abrmd"
SECTION = "security/tpm"

LICENSE = "BSD-2-Clause"
LIC_FILES_CHKSUM = "file://${S}/LICENSE;md5=500b2e742befc3da00684d8a1d5fd9da"

DEPENDS = "autoconf-archive-native dbus glib-2.0 glib-2.0-native pkgconfig libtss2"
RDEPENDS_${PN} = "libgcc"

SRC_URI = " \
    file://tpm2-abrmd-init.sh \
    file://tpm2-abrmd.default \
"

EXTRA_OECONF += " \
    --with-systemdsystemunitdir=${systemd_system_unitdir} \
"

S = "${WORKDIR}/${BPN}-${PV}"
inherit autotools pkgconfig systemd update-rc.d useradd

SYSTEMD_PACKAGES = "${PN}"
SYSTEMD_SERVICE_${PN} = "tpm2-abrmd.service"

INITSCRIPT_NAME = "tpm2-abrmd"
INITSCRIPT_PARAMS = "start 99 2 3 4 5 . stop 19 0 1 6 ."

USERADD_PACKAGES = "${PN}"
GROUPADD_PARAM_${PN} = "tss"
USERADD_PARAM_${PN} = "-M -d /var/lib/tpm -s /bin/false -g tss tss"

do_install_append() {
        install -d ${D}${sysconfdir}/init.d
        install -m 0755 ${WORKDIR}/tpm2-abrmd-init.sh ${D}${sysconfdir}/init.d/tpm2-abrmd
        install -d ${D}${sysconfdir}/default
        install -m 0644 ${WORKDIR}/tpm2-abrmd.default ${D}${sysconfdir}/default/tpm2-abrmd
}

FILES_${PN} += " \
    ${libdir}/systemd/system-preset/tpm2-abrmd.preset \
    ${datadir}/dbus-1/system-services/com.intel.tss2.Tabrmd.service \
"
