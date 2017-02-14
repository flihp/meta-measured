FILESEXTRAPATHS_prepend := "${THISDIR}/linux-yocto:"

# Enable kernel support if the feature is available on the machine.
SRC_URI += " \
    ${@bb.utils.contains('MACHINE_FEATURES', 'tpm', 'file://tpm.scc', '', d)} \
    ${@bb.utils.contains('MACHINE_FEATURES', 'tpm', 'file://tpm.cfg', '', d)} \    
    ${@bb.utils.contains('MACHINE_FEATURES', 'txt', 'file://txt.scc', '', d)} \
    ${@bb.utils.contains('MACHINE_FEATURES', 'txt', 'file://txt.cfg', '', d)} \
    ${@bb.utils.contains('DISTRO_FEATURES',  'ima', 'file://ima.scc', '', d)} \
    ${@bb.utils.contains('DISTRO_FEATURES',  'ima', 'file://ima.cfg', '', d)} \
"

