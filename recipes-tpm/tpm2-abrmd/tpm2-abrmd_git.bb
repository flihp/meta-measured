SUMMARY = ""
DESCRIPTION = ""
SECTION = "tpm"

LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://${S}/LICENSE;md5=500b2e742befc3da00684d8a1d5fd9da"

DEPENDS = "autoconf-archive dbus glib-2.0 pkgconfig libtctidevice libtss2"
RDEPENDS_${PN} = "libgcc"

SRC_URI = " \
    git://github.com/01org/tpm2-abrmd.git;protocol=git;branch=master;name=tpm2-abrmd;destsuffix=tpm2-abrmd \
    file://tpm2-abrmd-init.sh \
    file://tpm2-abrmd.default \
    "

# https://lists.yoctoproject.org/pipermail/yocto/2013-November/017042.html
SRCREV = "${AUTOREV}"
PVBASE := "${PV}"
PV = "${PVBASE}.${SRCPV}"
S = "${WORKDIR}/${BPN}"

inherit autotools pkgconfig systemd update-rc.d useradd

SYSTEMD_SERVICE = "tabrmd.service"

INITSCRIPT_NAME = "tpm2-abrmd"
INITSCRIPT_PARAMS = "start 99 2 3 4 5 . stop 19 0 1 6 ."

USERADD_PACKAGES = "${PN}"
GROUPADD_PARAM_${PN} = "tss"
USERADD_PARAM_${PN} = "-M -d /var/lib/tpm -s /bin/false -g tss tss"

# break out tcti into a package: libtcti-tabrmd
# package up the service file

do_configure_prepend () {
	# execute the bootstrap script
	currentdir=$(pwd)
	cd ${S}
	ACLOCAL="aclocal --system-acdir=${WORKDIR}/aclocal-copy" ./bootstrap --force
	cd ${currentdir}
}

do_install_append() {
        install -d ${D}${sysconfdir}/init.d
        install -m 0755 ${WORKDIR}/tpm2-abrmd-init.sh ${D}${sysconfdir}/init.d/tpm2-abrmd
        install -d ${D}${sysconfdir}/default
        install -m 0644 ${WORKDIR}/tpm2-abrmd.default ${D}${sysconfdir}/default/tpm2-abrmd
}

BBCLASSEXTEND = "native"
