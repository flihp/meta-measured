include tpm2-tools.inc

DEFAULT_PREFERENCE = "-1"

BRANCH = "master"

SRC_URI = " \
    git://github.com/tpm2-software/tpm2-tools.git;branch=${BRANCH} \
    "

S = "${WORKDIR}/git"

SRCREV = "${AUTOREV}"
PVBASE := "${PV}"
PV = "${PVBASE}.${SRCPV}"

do_configure_prepend () {
    currentdir=$(pwd)
    cd ${S}
    AUTORECONF=true ./bootstrap
    cd ${currentdir}
}
