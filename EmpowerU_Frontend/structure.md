src/
├── app/
│   ├── core/
│   │   ├── components/
│   │   │   ├── navbar/
│   │   │   │   ├── navbar.component.ts
│   │   │   │   ├── navbar.component.html
│   │   │   │   ├── navbar.component.scss
│   │   │   ├── footer/
│   │   │       ├── footer.component.ts
│   │   │       ├── footer.component.html
│   │   │       ├── footer.component.scss
│   │   ├── services/
│   │   │   ├── auth.service.ts
│   │   │   ├── api.service.ts
│   │   │   ├── notification.service.ts
│   │   ├── interceptors/
│   │   │   ├── auth.interceptor.ts
│   │   │   ├── error.interceptor.ts
│   │   ├── guards/
│   │       ├── auth.guard.ts
│   │       ├── admin.guard.ts
│   │   core.module.ts
│   │
│   ├── shared/
│   │   ├── components/
│   │   │   ├── loader/
│   │   │   │   ├── loader.component.ts
│   │   │   │   ├── loader.component.html
│   │   │   │   ├── loader.component.scss
│   │   │   ├── pagination/
│   │   │       ├── pagination.component.ts
│   │   │       ├── pagination.component.html
│   │   │       ├── pagination.component.scss
│   │   ├── pipes/
│   │   │   ├── capitalize.pipe.ts
│   │   │   ├── date-format.pipe.ts
│   │   ├── directives/
│   │       ├── highlight.directive.ts
│   │   shared.module.ts
│   │
│   ├── modules/
│   │   ├── user/
│   │   │   ├── components/
│   │   │   │   ├── login/
│   │   │   │   │   ├── login.component.ts
│   │   │   │   │   ├── login.component.html
│   │   │   │   ├── register/
│   │   │   │   │   ├── register.component.ts
│   │   │   │   │   ├── register.component.html
│   │   │   │   ├── profile/
│   │   │   │       ├── profile.component.ts
│   │   │   │       ├── profile.component.html
│   │   │   ├── services/
│   │   │       ├── user.service.ts
│   │   │   user.module.ts
│   │   │   user-routing.module.ts
│   │   │
│   │   ├── training-program/
│   │   │   ├── components/
│   │   │   │   ├── program-list/
│   │   │   │   │   ├── program-list.component.ts
│   │   │   │   │   ├── program-list.component.html
│   │   │   │   ├── program-details/
│   │   │   │   │   ├── program-details.component.ts
│   │   │   │   │   ├── program-details.component.html
│   │   │   │   ├── course-list/
│   │   │   │   │   ├── course-list.component.ts
│   │   │   │   │   ├── course-list.component.html
│   │   │   │   ├── course-details/
│   │   │   │       ├── course-details.component.ts
│   │   │   │       ├── course-details.component.html
│   │   │   ├── services/
│   │   │       ├── training-program.service.ts
│   │   │   training-program.module.ts
│   │   │   training-program-routing.module.ts
│   │   │
│   │   ├── enrollment/
│   │   │   ├── components/
│   │   │   │   ├── enrollment-list/
│   │   │   │   │   ├── enrollment-list.component.ts
│   │   │   │   │   ├── enrollment-list.component.html
│   │   │   │   ├── enroll/
│   │   │   │       ├── enroll.component.ts
│   │   │   │       ├── enroll.component.html
│   │   │   ├── services/
│   │   │       ├── enrollment.service.ts
│   │   │   enrollment.module.ts
│   │   │   enrollment-routing.module.ts
│   │   │
│   │   ├── progress/
│   │   │   ├── components/
│   │   │   │   ├── progress-dashboard/
│   │   │   │   │   ├── progress-dashboard.component.ts
│   │   │   │   │   ├── progress-dashboard.component.html
│   │   │   │   ├── progress-details/
│   │   │   │       ├── progress-details.component.ts
│   │   │   │       ├── progress-details.component.html
│   │   │   ├── services/
│   │   │       ├── progress.service.ts
│   │   │   progress.module.ts
│   │   │   progress-routing.module.ts
│   │   │
│   │   ├── feedback/
│   │   │   ├── components/
│   │   │   │   ├── feedback-list/
│   │   │   │   │   ├── feedback-list.component.ts
│   │   │   │   │   ├── feedback-list.component.html
│   │   │   │   ├── feedback-form/
│   │   │   │   │   ├── feedback-form.component.ts
│   │   │   │   │   ├── feedback-form.component.html
│   │   │   │   ├── feedback-summary/
│   │   │   │       ├── feedback-summary.component.ts
│   │   │   │       ├── feedback-summary.component.html
│   │   │   ├── services/
│   │   │       ├── feedback.service.ts
│   │   │   feedback.module.ts
│   │   │   feedback-routing.module.ts
│   │   │
│   │   ├── admin/
│   │       ├── components/
│   │       │   ├── dashboard/
│   │       │   │   ├── dashboard.component.ts
│   │       │   │   ├── dashboard.component.html
│   │       │   ├── manage-users/
│   │       │   │   ├── manage-users.component.ts
│   │       │   │   ├── manage-users.component.html
│   │       │   ├── manage-feedback/
│   │       │       ├── manage-feedback.component.ts
│   │       │       ├── manage-feedback.component.html
│   │       ├── services/
│   │           ├── admin.service.ts
│   │       admin.module.ts
│   │       admin-routing.module.ts
│   │
│   ├── app-routing.module.ts
│   ├── app.module.ts
│   ├── app.component.ts
│   ├── app.component.html
│
├── assets/
│   ├── images/
│   ├── styles/
│       ├── variables.scss
│       ├── global.scss
├── environments/
│   ├── environment.ts
│   ├── environment.prod.ts
