DESCRIPTION = "Basic packagegroup for TCG TSS and utilities that use it."
LICENSE = "MIT"
PR = "r0"

inherit packagegroup

PROVIDES = "packagegroup-tpm"

RDEPENDS_packagegroup-tpm = "\
    trousers \
    tpm-tools \
    kernel-module-tpm-nsc \
    kernel-module-tpm-atmel \
    kernel-module-tpm-infineon \
    "
