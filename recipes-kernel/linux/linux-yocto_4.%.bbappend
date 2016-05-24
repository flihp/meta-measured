FILESEXTRAPATHS_prepend := "${THISDIR}/linux-yocto:"

# Enable kernel support if the feature is available on the machine.
SRC_URI += " \
    ${@base_contains('MACHINE_FEATURES', 'tpm', 'file://tpm.scc', '', d)} \
    ${@base_contains('MACHINE_FEATURES', 'tpm', 'file://tpm.cfg', '', d)} \    
    ${@base_contains('MACHINE_FEATURES', 'tpm2','file://tpm2.scc','', d)} \
    ${@base_contains('MACHINE_FEATURES', 'tpm2','file://tpm2.cfg','', d)} \
    ${@base_contains('MACHINE_FEATURES', 'txt', 'file://txt.scc', '', d)} \
    ${@base_contains('MACHINE_FEATURES', 'txt', 'file://txt.cfg', '', d)} \
    ${@base_contains('DISTRO_FEATURES',  'ima', 'file://ima.scc', '', d)} \
    ${@base_contains('DISTRO_FEATURES',  'ima', 'file://ima.cfg', '', d)} \
"

