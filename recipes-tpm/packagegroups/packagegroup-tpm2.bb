DESCRIPTION = "Packagegroup for TPM2 TCG TSS userspace and utilities."
LICENSE = "MIT"
PR = "r0"

inherit packagegroup

PROVIDES = "packagegroup-tpm2"

RDEPENDS_packagegroup-tpm2 = "\
    libtss2 \
    libtctidevice \
    libtctisocket \
    kernel-module-tpm-crb \
    tpm2-abrmd \
    tpm2-tools \
"
