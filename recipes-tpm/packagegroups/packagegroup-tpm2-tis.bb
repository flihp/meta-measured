DESCRIPTION = "Support for TPM 2.0 device exposed using TIS interface."
LICENSE = "MIT"
PR = "r0"

inherit packagegroup

RRECOMMENDS_packagegroup-tpm2-tis = " \
    kernel-module-tpm-tis \
    libtss2 \
    tpm2-abrmd \
    tpm2-tools \
"
