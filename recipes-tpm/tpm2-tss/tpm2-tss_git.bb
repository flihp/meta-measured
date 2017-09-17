include ${BPN}.inc

DEFAULT_PREFERENCE = "-1"

SRC_URI = "git://github.com/01org/${BPN}.git;protocol=git;branch=master;name=${BPN};destsuffix=${BPN}"

# https://lists.yoctoproject.org/pipermail/yocto/2013-November/017042.html
SRCREV = "${AUTOREV}"
PVBASE := "${PV}"
PV = "${PVBASE}.${SRCPV}"
