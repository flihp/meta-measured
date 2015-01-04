SUMMARY = "Python library and tools for doing stuff with EFL files."
DESCRIPTION = " \
    pyelftools is a pure-Python library for parsing and analyzing ELF files \
    and DWARF debugging information. See the User's guide for more details. \
"
SECTION = "devtools"
PR = "p0"
LICENSE = "Unlicense"
S = "${WORKDIR}/git"
LIC_FILES_CHKSUM = "file://LICENSE;md5=8ffb8d54e2d57d6b201d43878fc1ed98"
RDEPENDS_${PN} = "python"
SRC_URI += " \
    git://github.com/eliben/pyelftools \
"
SRCREV = "master"

inherit distutils
