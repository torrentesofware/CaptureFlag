import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './aboutus.reducer';
import { IAboutus } from 'app/shared/model/aboutus.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IAboutusDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const AboutusDetail = (props: IAboutusDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { aboutusEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2>
          Aboutus [<b>{aboutusEntity.id}</b>]
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="title">Title</span>
          </dt>
          <dd>{aboutusEntity.title}</dd>
          <dt>
            <span id="image">Image</span>
          </dt>
          <dd>{aboutusEntity.image}</dd>
          <dt>
            <span id="description">Description</span>
          </dt>
          <dd>{aboutusEntity.description}</dd>
        </dl>
        <Button tag={Link} to="/aboutus" replace color="info">
          <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/aboutus/${aboutusEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ aboutus }: IRootState) => ({
  aboutusEntity: aboutus.entity,
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(AboutusDetail);
