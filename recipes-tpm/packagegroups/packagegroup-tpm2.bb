DESCRIPTION = "Packagegroup for TPM2 TCG TSS userspace and utilities."
LICENSE = "MIT"
PR = "r0"

inherit packagegroup

PROVIDES = "packagegroup-tpm2"

RDEPENDS_packagegroup-tpm2 = "\
    libtss2 \
    libtctidevice \
    libtctisocket \
    tpm2-abrmd \
    tpm2-tools \
"

# Kernel module will be installed if it was build as a module (=m).
# At the same time, the build will not fail if package is not available when
# the module is compiled in kernel (=y)
RRECOMMENDS_packagegroup-tpm2 = "\
    kernel-module-tpm-crb \
"
