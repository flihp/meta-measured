SUMMARY = "Python library and tools for doing stuff with EFL files."
DESCRIPTION = " \
    pyelftools is a pure-Python library for parsing and analyzing ELF files \
    and DWARF debugging information. See the User's guide for more details. \
"
SECTION = "devtools"
PR = "p0"
LICENSE = "PD"
S = "${WORKDIR}/git"
LIC_FILES_CHKSUM = "file://LICENSE;md5=5ce2a2b07fca326bc7c146d10105ccfc"
RDEPENDS_${PN} = "python python-contextlib python-debugger python-pprint"
RDEPENDS_${PN}_class-native = "python"
SRC_URI += " \
    git://github.com/eliben/pyelftools \
"
SRCREV = "master"

inherit distutils

BBCLASSEXTEND = "native"
