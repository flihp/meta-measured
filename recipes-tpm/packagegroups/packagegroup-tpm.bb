DESCRIPTION = "Basic packagegroup for TCG TSS and utilities that use it."
LICENSE = "MIT"
PR = "r0"

inherit packagegroup

PROVIDES = " \
    ${@bb.utils.contains("MACHINE_FEATURES", 'tpm', 'packagegroup-tpm', '', d)} \
"

SUMMARY_packagegroup-tpm = "TPM 1.2 support"
RRECOMMENDS_packagegroup-tpm = " \
    kernel-module-tpm-nsc \
    kernel-module-tpm-atmel \
    kernel-module-tpm-infineon \
    trousers \
    tpm-tools \
"
