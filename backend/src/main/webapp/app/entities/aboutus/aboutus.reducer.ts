import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IAboutus, defaultValue } from 'app/shared/model/aboutus.model';

export const ACTION_TYPES = {
  FETCH_ABOUTUS_LIST: 'aboutus/FETCH_ABOUTUS_LIST',
  FETCH_ABOUTUS: 'aboutus/FETCH_ABOUTUS',
  CREATE_ABOUTUS: 'aboutus/CREATE_ABOUTUS',
  UPDATE_ABOUTUS: 'aboutus/UPDATE_ABOUTUS',
  DELETE_ABOUTUS: 'aboutus/DELETE_ABOUTUS',
  RESET: 'aboutus/RESET',
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IAboutus>,
  entity: defaultValue,
  updating: false,
  totalItems: 0,
  updateSuccess: false,
};

export type AboutusState = Readonly<typeof initialState>;

// Reducer

export default (state: AboutusState = initialState, action): AboutusState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_ABOUTUS_LIST):
    case REQUEST(ACTION_TYPES.FETCH_ABOUTUS):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true,
      };
    case REQUEST(ACTION_TYPES.CREATE_ABOUTUS):
    case REQUEST(ACTION_TYPES.UPDATE_ABOUTUS):
    case REQUEST(ACTION_TYPES.DELETE_ABOUTUS):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true,
      };
    case FAILURE(ACTION_TYPES.FETCH_ABOUTUS_LIST):
    case FAILURE(ACTION_TYPES.FETCH_ABOUTUS):
    case FAILURE(ACTION_TYPES.CREATE_ABOUTUS):
    case FAILURE(ACTION_TYPES.UPDATE_ABOUTUS):
    case FAILURE(ACTION_TYPES.DELETE_ABOUTUS):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload,
      };
    case SUCCESS(ACTION_TYPES.FETCH_ABOUTUS_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data,
        totalItems: parseInt(action.payload.headers['x-total-count'], 10),
      };
    case SUCCESS(ACTION_TYPES.FETCH_ABOUTUS):
      return {
        ...state,
        loading: false,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.CREATE_ABOUTUS):
    case SUCCESS(ACTION_TYPES.UPDATE_ABOUTUS):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.DELETE_ABOUTUS):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: {},
      };
    case ACTION_TYPES.RESET:
      return {
        ...initialState,
      };
    default:
      return state;
  }
};

const apiUrl = 'api/aboutuses';

// Actions

export const getEntities: ICrudGetAllAction<IAboutus> = (page, size, sort) => {
  const requestUrl = `${apiUrl}${sort ? `?page=${page}&size=${size}&sort=${sort}` : ''}`;
  return {
    type: ACTION_TYPES.FETCH_ABOUTUS_LIST,
    payload: axios.get<IAboutus>(`${requestUrl}${sort ? '&' : '?'}cacheBuster=${new Date().getTime()}`),
  };
};

export const getEntity: ICrudGetAction<IAboutus> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_ABOUTUS,
    payload: axios.get<IAboutus>(requestUrl),
  };
};

export const createEntity: ICrudPutAction<IAboutus> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_ABOUTUS,
    payload: axios.post(apiUrl, cleanEntity(entity)),
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IAboutus> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_ABOUTUS,
    payload: axios.put(apiUrl, cleanEntity(entity)),
  });
  return result;
};

export const deleteEntity: ICrudDeleteAction<IAboutus> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_ABOUTUS,
    payload: axios.delete(requestUrl),
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET,
});
