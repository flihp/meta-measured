DESCRIPTION = "Basic packagegroup for tboot & ACMs."
LICENSE = "MIT"
PR = "r0"

inherit packagegroup

PROVIDES = "${PACKAGES}"
PACKAGES = "${@base_contains('DISTRO_FEATURES', 'txt', 'packagegroup-tboot', '',d)}"

RDEPENDS_packagegroup-tboot = "\
    tboot \
    3rd-gen-i5-i7-sinit \
    4th-gen-i5-i7-sinit \
    "
