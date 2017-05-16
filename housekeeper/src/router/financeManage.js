import apartmentWallet from '@/components/financeManage/apartmentWallet'
import repaymentPlan from '@/components/financeManage/repaymentPlan'
export default [
  {
    path: '/apartmentWallet',
    name: 'apartmentWallet',
    component: apartmentWallet
  },
  {
    path: '/repaymentPlan',
    name: 'repaymentPlan',
    component: repaymentPlan
  }
]
