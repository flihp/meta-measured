SUMMARY = "Tools necessary to calculate PCR values."
DESCRIPTION = " \
    A collection of C and python scripts to calculate PCR values. \
    This includes the TXT / tboot PCRs 17, 18 and 19. \
"
HOMEPAGE = "https://github.com/flihp/pcr-calc"
SECTION = "txt"
PR = "p0"
LICENSE = "GPL-2.0"
S = "${WORKDIR}/git"
LIC_FILES_CHKSUM = "file://COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263"
DEPENDS = "pyelftools-native"
RDEPENDS_${PN} = "python python-argparse python-compression python-mmap python-netclient python-textutils pyelftools"
SRC_URI += " \
    git://github.com/flihp/pcr-calc.git \
"
SRCREV = "master"

inherit autotools distutils-base
