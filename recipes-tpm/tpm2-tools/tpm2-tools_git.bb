include tpm2-tools.inc

DEFAULT_PREFERENCE = "-1"

# https://lists.yoctoproject.org/pipermail/yocto/2013-November/017042.html
SRCREV = "${AUTOREV}"
PVBASE := "${PV}"
PV = "${PVBASE}.${SRCPV}"

SRC_URI = " \
    git://github.com/01org/tpm2.0-tools.git;protocol=git;branch=master;name=tpm2.0-tools;destsuffix=tpm2.0-tools \
"
