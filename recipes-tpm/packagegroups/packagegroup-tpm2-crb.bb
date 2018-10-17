DESCRIPTION = "Support for TPM 2.0 device exposed using CRB interface."
LICENSE = "MIT"
PR = "r0"

inherit packagegroup

RRECOMMENDS_packagegroup-tpm2-crb = " \
    kernel-module-tpm-crb \
    libtss2 \
    tpm2-abrmd \
    tpm2-tools \
"
