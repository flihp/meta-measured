FILESEXTRAPATHS_prepend := "${THISDIR}/linux-yocto:"

# Enable kernel support if the feature is available on the machine.
SRC_URI += " \
    ${@bb.utils.contains('MACHINE_FEATURES', 'tpm', 'file://tpm-tis.scc', '', d)} \
    ${@bb.utils.contains('MACHINE_FEATURES', 'tpm', 'file://tpm-tis.cfg', '', d)} \
    ${@bb.utils.contains('MACHINE_FEATURES', 'tpm2-tis', 'file://tpm2-tis.scc', '', d)} \
    ${@bb.utils.contains('MACHINE_FEATURES', 'tpm2-tis', 'file://tpm2-tis.cfg', '', d)} \
    ${@bb.utils.contains('MACHINE_FEATURES', 'tpm2-crb','file://tpm2-crb.scc','', d)} \
    ${@bb.utils.contains('MACHINE_FEATURES', 'tpm2-crb','file://tpm2-crb.cfg','', d)} \
    ${@bb.utils.contains('MACHINE_FEATURES', 'txt', 'file://txt.scc', '', d)} \
    ${@bb.utils.contains('MACHINE_FEATURES', 'txt', 'file://txt.cfg', '', d)} \
"
