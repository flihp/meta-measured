DESCRIPTION = "Utilities for interacting with TXT."
LICENSE = "MIT"
PR = "r0"

inherit packagegroup

PROVIDES = "${PACKAGES}"
PACKAGES = "${@base_contains('DISTRO_FEATURES', 'txt', 'packagegroup-txt-utils', '',d)}"

RDEPENDS_packagegroup-txt-utils = "\
    pcr-calc \
    "
