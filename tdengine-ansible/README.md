# TDengine Ansible Deployment

This project provides Ansible playbooks and roles for deploying a three-node TDengine cluster without Docker, Kubernetes or Helm. The deployment can target different environments (`alpha`, `beta`).

## Directory Layout

- `inventories/` – contains environment specific inventories.
- `roles/tdengine/` – role to install or remove TDengine.
- `playbooks/` – main playbooks for deployment and removal.

## Usage

1. Install Ansible on your control machine.
2. Update the inventory under `inventories/<env>/hosts` with the real host IPs or names.
3. Set environment-specific variables in `inventories/<env>/group_vars/all.yml`.
4. Ensure target hosts can reach the official TDengine repository. The role
   automatically adds the apt repository and installs the package.

### Deploy

```bash
ansible-playbook -i inventories/alpha/hosts playbooks/deploy.yml
```

### Remove

```bash
ansible-playbook -i inventories/alpha/hosts playbooks/remove.yml
```

Replace `alpha` with `beta` to target the beta environment.
