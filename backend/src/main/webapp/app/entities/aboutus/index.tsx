import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import Aboutus from './aboutus';
import AboutusDetail from './aboutus-detail';
import AboutusUpdate from './aboutus-update';
import AboutusDeleteDialog from './aboutus-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={AboutusUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={AboutusUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={AboutusDetail} />
      <ErrorBoundaryRoute path={match.url} component={Aboutus} />
    </Switch>
    <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={AboutusDeleteDialog} />
  </>
);

export default Routes;
