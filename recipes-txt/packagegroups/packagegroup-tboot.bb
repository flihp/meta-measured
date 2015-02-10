DESCRIPTION = "Basic packagegroup for tboot & ACMs."
LICENSE = "MIT"
PR = "r0"

inherit packagegroup

PROVIDES = "packagegroup-tboot"

RDEPENDS_packagegroup-tboot = "\
    tboot \
    3rd-gen-i5-i7-sinit \
    4th-gen-i5-i7-sinit \
    5th-gen-i5-i7-sinit \
    "
