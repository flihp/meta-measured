include ${BPN}.inc

DEFAULT_PREFERENCE = "-1"

SRC_URI = "git://github.com/tpm2-software/${BPN}.git;protocol=git;branch=master;name=${BPN};destsuffix=${BPN}"

S = "${WORKDIR}/${BPN}"

# https://lists.yoctoproject.org/pipermail/yocto/2013-November/017042.html
SRCREV = "${AUTOREV}"
PVBASE := "${PV}"
PV = "${PVBASE}.${SRCPV}"

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
