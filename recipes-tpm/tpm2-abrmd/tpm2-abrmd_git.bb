include ${BPN}.inc

DEFAULT_PREFERENCE = "-1"

SRC_URI += "git://github.com/01org/tpm2-abrmd;protocol=git;branch=master;name=tpm2-abrmd;destsuffix=tpm2-abrmd"

# https://lists.yoctoproject.org/pipermail/yocto/2013-November/017042.html
SRCREV = "${AUTOREV}"
PVBASE := "${PV}"
PV = "${PVBASE}.${SRCPV}"
S = "${WORKDIR}/${BPN}"

do_configure_prepend () {
	# execute the bootstrap script
	currentdir=$(pwd)
	cd ${S}
	ACLOCAL="aclocal --system-acdir=${STAGING_DATADIR}/aclocal" ./bootstrap
	cd ${currentdir}
}
