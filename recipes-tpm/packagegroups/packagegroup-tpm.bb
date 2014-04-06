DESCRIPTION = "Basic packagegroup for TCG TSS and utilities that use it."
LICENSE = "MIT"
PR = "r0"

inherit packagegroup

PROVIDES = "${PACKAGES}"
PACKAGES = "${@base_contains('DISTRO_FEATURES', 'tpm', 'packagegroup-tpm', '',d)}"

RDEPENDS_packagegroup-tpm = "\
    trousers \
    tpm-tools \
    kernel-module-tpm \
    kernel-module-tpm-tis \
    kernel-module-tpm-nsc \
    kernel-module-tpm-atmel \
    kernel-module-tpm-infineon \
    "
