SUMMARY = ""
DESCRIPTION = ""
SECTION = "tpm"

LICENSE = "BSD-2-Clause"
LIC_FILES_CHKSUM = "file://${S}/LICENSE;md5=500b2e742befc3da00684d8a1d5fd9da"

DEPENDS = "autoconf-archive-native dbus glib-2.0 glib-2.0-native pkgconfig libtctidevice libtss2"
RDEPENDS_${PN} = "libgcc"

SRC_URI = " \
    file://tpm2-abrmd-init.sh \
    file://tpm2-abrmd.default \
"

S = "${WORKDIR}/${BPN}-${PV}"
inherit autotools pkgconfig systemd update-rc.d useradd

# this breaks the build?
CONFIGUREOPTS += " \
    --with-systemdsystemunitdir=${systemd_system_unitdir} \
    --with-udevrulesdir=${sysconfdir}/udev/rules.d \
"

SYSTEMD_SERVICE = "tpm2-abrmd.service"

INITSCRIPT_NAME = "tpm2-abrmd"
INITSCRIPT_PARAMS = "start 99 2 3 4 5 . stop 19 0 1 6 ."

USERADD_PACKAGES = "${PN}"
GROUPADD_PARAM_${PN} = "tss"
USERADD_PARAM_${PN} = "-M -d /var/lib/tpm -s /bin/false -g tss tss"

# break out tcti into a package: libtcti-tabrmd
# package up the service file

do_install_append() {
        install -d ${D}${sysconfdir}/init.d
        install -m 0755 ${WORKDIR}/tpm2-abrmd-init.sh ${D}${sysconfdir}/init.d/tpm2-abrmd
        install -d ${D}${sysconfdir}/default
        install -m 0644 ${WORKDIR}/tpm2-abrmd.default ${D}${sysconfdir}/default/tpm2-abrmd
}
