SUMMARY = "Tools necessary to calculate PCR values."
DESCRIPTION = " \
    A collection of C and python scripts to calculate PCR values. \
    This includes the TXT / tboot PCRs 17, 18 and 19. \
"
SECTION = "txt"
PR = "p0"
LICENSE = "GPL-2.0"
S = "${WORKDIR}/git"
LIC_FILES_CHKSUM = "file://COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263"
RDEPENDS_${PN} = "python python-argparse python-mmap python-netclient python-textutils"
SRC_URI += " \
    git://github.com/flihp/pcr-calc.git \
"
SRCREV = "master"
FILES_${PN} += "${libdir}/python${PYTHON_BASEVERSION}/site-packages/pcr-calc"

inherit autotools
