'use strict';

/**
 * Guest.js controller
 *
 * @description: A set of functions called "actions" for managing `Guest`.
 */

module.exports = {

  /**
   * Retrieve guest records.
   *
   * @return {Object|Array}
   */

  find: async (ctx) => {
    if (ctx.query._q) {
      return strapi.services.guest.search(ctx.query);
    } else {
      return strapi.services.guest.fetchAll(ctx.query);
    }
  },

  /**
   * Retrieve a guest record.
   *
   * @return {Object}
   */

  findOne: async (ctx) => {
    if (!ctx.params._id.match(/^[0-9a-fA-F]{24}$/)) {
      return ctx.notFound();
    }

    return strapi.services.guest.fetch(ctx.params);
  },

  /**
   * Count guest records.
   *
   * @return {Number}
   */

  count: async (ctx) => {
    return strapi.services.guest.count(ctx.query);
  },

  /**
   * Create a/an guest record.
   *
   * @return {Object}
   */

  create: async (ctx) => {
    return strapi.services.guest.add(ctx.request.body);
  },

  /**
   * Update a/an guest record.
   *
   * @return {Object}
   */

  update: async (ctx, next) => {
    return strapi.services.guest.edit(ctx.params, ctx.request.body) ;
  },

  /**
   * Destroy a/an guest record.
   *
   * @return {Object}
   */

  destroy: async (ctx, next) => {
    return strapi.services.guest.remove(ctx.params);
  }
};
