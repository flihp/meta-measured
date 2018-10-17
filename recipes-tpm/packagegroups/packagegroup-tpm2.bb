DESCRIPTION = "Packagegroup for TPM2 TCG TSS userspace and utilities."
LICENSE = "MIT"
PR = "r0"

inherit packagegroup

PROVIDES = " \
    ${@bb.utils.contains("MACHINE_FEATURES", 'tpm2', 'packagegroup-tpm2', '', d)} \
"

SUMMARY_packagegroup-tpm2 = "TPM 2.0 support"
RRECOMMENDS_packagegroup-tpm2 = " \
    kernel-module-tpm-crb \
    libtss2 \
    tpm2-abrmd \
    tpm2-tools \
"
